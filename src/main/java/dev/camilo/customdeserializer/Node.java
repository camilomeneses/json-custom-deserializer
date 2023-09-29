package dev.camilo.customdeserializer;

import java.util.List;

public record Node(
  String id,
  String title,
  String slug,
  String date,
  int timeToRead,
  List<Tag> tags
) {
  
}
