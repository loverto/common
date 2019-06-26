package org.ylf;

import java.util.Objects;

public class DiePattern {

    Long id;
    String diePatternType;
    ComputerType computerType ;
    String diePatternimagePath ;

    String fileName;

    @Override
    public String toString() {
        return "DiePattern{" +
            "id=" + id +
            ", diePatternType='" + diePatternType + '\'' +
            ", computerType=" + computerType +
            ", diePatternimagePath='" + diePatternimagePath + '\'' +
            ", fileName='" + fileName + '\'' +
            '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDiePatternType() {
        return diePatternType;
    }

    public void setDiePatternType(String diePatternType) {
        this.diePatternType = diePatternType;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public String getDiePatternimagePath() {
        return diePatternimagePath;
    }

    public void setDiePatternimagePath(String diePatternimagePath) {
        this.diePatternimagePath = diePatternimagePath;
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
        DiePattern that = (DiePattern) o;
        return Objects.equals(diePatternType, that.diePatternType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diePatternType);
    }
}
