package org.ylf;

public class RecommendedStatus
{
  Long id = 1l;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "RecommendedStatus{" +
        "id=" + id +
        '}';
  }
}
