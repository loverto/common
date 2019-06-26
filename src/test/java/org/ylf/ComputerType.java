package org.ylf;

import java.util.Objects;

public class ComputerType {

    Long id;
    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerType that = (ComputerType) o;
        return Objects.equals(value.toUpperCase(), that.value.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ComputerType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
