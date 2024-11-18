package com.example.mart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.QItem;
import com.example.mart.entity.item.QMember;
import com.querydsl.jpa.JPQLQuery;

public class QueryDslOrderRepositoryImpl extends QuerydslRepositorySupport implements QueryDslOrderRepository {
    public QueryDslOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Member> members() {
        // select * from member where name='user1' order by name desc
        QMember qMember = QMember.member;
        JPQLQuery<Member> query = from(qMember);
        query.where(qMember.name.eq("user1")).orderBy(qMember.name.desc());
        JPQLQuery<Member> tuple = query.select(qMember);
        System.out.println(tuple);
        return tuple.fetch();
    }

    @Override
    public List<Item> items() {
        // select * from item where name='아파트' and price >10000
        QItem qItem = QItem.item;
        JPQLQuery<Item> tuple = from(qItem).where(qItem.name.eq("아파트").and(qItem.price.gt(10000))).select(qItem);

        System.out.println("tuple " + tuple);
        return tuple.fetch();
    }

    @Override
    public List<Object[]> joinTest() {
        return null;
    }
}
