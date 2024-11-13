package com.example.book.service;

import java.util.List;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

public interface BookService {
    // crud
    Long create(BookDTO dto);

    BookDTO getRow(Long id);

    PageResultDto<BookDTO, Book> getList(PageRequestDto requestDto);

    Long update(BookDTO dto);

    void delete(Long id);

    List<CategoryDTO> getCateList();

    List<PublisherDTO> getPubList();

    public default CategoryDTO entityToDto(Category entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getName())
                .build();
    }

    public default Category dtoToEntity(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getCategoryName())
                .build();
    }

    public default PublisherDTO entityToDto(Publisher entity) {
        return PublisherDTO.builder()
                .id(entity.getId())
                .publisherName(entity.getName())
                .build();
    }

    public default Publisher dtoToEntity(PublisherDTO dto) {
        return Publisher.builder()
                .id(dto.getId())
                .name(dto.getPublisherName())
                .build();
    }

    public default BookDTO entityToDto(Book entity) {
        return BookDTO.builder().id(entity.getId())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .categoryName(entity.getCategory().getName())
                .publisherName(entity.getPublisher().getName())
                .price(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .createdDateTime(entity.getCreatedDateTime())
                .lastModifDateTime(entity.getLastModifDateTime())
                .build();
    }

    public default Book dtoToEntity(BookDTO dto) {
        return Book.builder().id(dto.getId())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .price(dto.getPrice())
                .salePrice(dto.getSalePrice())
                .category(Category.builder().id(Long.parseLong(dto.getCategoryName())).build())
                .publisher(Publisher.builder().id(Long.parseLong(dto.getPublisherName())).build())
                .build();
    }

}
