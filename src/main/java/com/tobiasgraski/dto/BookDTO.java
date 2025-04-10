package com.tobiasgraski.dto;

public record BookDTO(String title, int authorId, String isbn, int publicationYear, double price, int stock) {
}
