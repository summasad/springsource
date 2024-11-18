package com.example.mart.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.entity.product.Album;
import com.example.mart.entity.product.Book;
import com.example.mart.entity.product.Movie;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import oracle.net.aso.l;
import oracle.net.aso.m;

// 객체 그래프 탐색

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // C
    @Test
    public void memberInsertTest() {
        memberRepository.save(Member.builder().name("user1").city("서울시").street("123-45").zipcode("15100").build());
        memberRepository.save(Member.builder().name("user2").city("경기도").street("123-46").zipcode("16100").build());
        memberRepository.save(Member.builder().name("user3").city("인천시").street("123-47").zipcode("17100").build());

    }

    @Test
    public void itemInsertTest() {
        // itemRepository.save(Item.builder().name("tshirt").price(40000).quantity(10).build());
        // itemRepository.save(Item.builder().name("skirt").price(50000).quantity(20).build());
        // itemRepository.save(Item.builder().name("hood").price(60000).quantity(30).build());
        Album album = new Album();
        album.setArtist("로제");
        album.setName("아파트");
        album.setPrice(15200);
        album.setQuantity(15);
        itemRepository.save(album);

        Book book = new Book();
        book.setAuthor("한강");
        book.setIsbn("122");
        book.setName("소년이온다");
        book.setPrice(10000);
        book.setQuantity(15);
        itemRepository.save(book);

        Movie movie = new Movie();
        movie.setActor("폴 메스칼");
        movie.setDirector("리들리 스콧");
        movie.setName("글래디에이터2");
        movie.setPrice(25000);
        movie.setQuantity(300);
        itemRepository.save(movie);
    }

    @Test
    public void orderInsertTest() {
        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(1L).get();

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
    }

    // R
    @Test
    public void memberAndItemOrderListTest() {

        // 주문내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));

        // 주문 상세 내역
        // orderRepository.findAll().forEach(order -> {
        // System.out.println(order);
        // System.out.println(order.getMember());
        // });

        orderItemRepository.findAll().forEach(orderItem -> {

            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문 상세 내역 조회
            System.out.println(orderItem.getOrder());
            // 주문자 상세 조회
            System.out.println(orderItem.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {

        // 주문 하나 조회
        OrderItem orderItem = orderItemRepository.findById(1L).get();
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문 상세 내역 조회
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());

    }

    // U
    @Test
    public void memberAndItemAndOrderUpdateTest() {

        // 주문자의 주소 변경
        // Member member = Member.builder()
        // .id(1L)
        // .city("서울시")
        // .name("user1")
        // .street("255-10")
        // .zipcode("15100")
        // .build();
        Member member = memberRepository.findById(1L).get();
        member.setStreet("432-10");

        // save : insert or update
        // 엔티티 매니저가 있어서 현재 entity 가 new 인지 기존 entity 인지 구분이 가능함
        // new : insert 호출 / 기존 entity : update 호출
        // update 는 무조건 전체 컬럼이 수정되는 형태로 작성됨
        System.out.println(memberRepository.save(member));

        // 1번 주문 상품의 가격 변경
        // 아이템 수량, 가격 변경
        Item item = itemRepository.findById(1L).get();
        item.setPrice(102000);
        itemRepository.save(item);

        // 연결된 테이블 모두 수정
        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.setPrice(102000);
        orderItemRepository.save(orderItem);

    }

    // D
    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문 취소
        // 무결성 제약조건(C##JPAUSER.FK5ODJTCYIRCY7XU4QTOLJDHKRJ)이 위배되었습니다- 자식 레코드가 발견되었습니다
        // 주문상품 취소
        orderItemRepository.deleteById(1L);

        // 주문 취소
        orderRepository.deleteById(1L);
    }

    // 양방향
    // Order ==> OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemList() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        // Order ==> OrderItem 탐색 시도
        // org.hibernate.LazyInitializationException => 패치전략 바꾸기
        order.getOrderItemsList().forEach(orderItem -> System.out.println(orderItem));
    }

    @Transactional
    @Test
    public void testOrderList() {
        Member member = memberRepository.findById(1L).get();
        System.out.println(member);

        member.getOrders().forEach(order -> System.out.println(order));

    }

    // 일대일
    @Test
    public void testDeliveryInsert() {

        // 배송정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .street("동소문로1가")
                .zipcode("11051")
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        // order 와 배송정보 연결
        Order order = orderRepository.findById(2L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testOrderRead() {
        // order 조회(+ 배송정보)
        orderRepository.findAll().forEach(order -> {
            System.out.println(order);
            System.out.println(order.getDelivery());
        });

        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        System.out.println(order.getDelivery());

    }

    // 양방향(배송 => 주문)
    @Test
    public void testDeliveryRead() {
        // 배송정보 조회(+order)
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());

    }

    @Test
    public void testMembers() {
        System.out.println(orderRepository.members());

    }

}
