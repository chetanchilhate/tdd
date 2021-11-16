package com.medline.tdd.conversion;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Length {

  private final int value;
  private final Unit unit;

  public Length(int value, Unit unit) {
    this.value = value;
    this.unit = unit;
  }

  private double getConvertedValue() {
    return value * unit.conversionFactor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Length length = (Length) o;

    return new EqualsBuilder().append(getConvertedValue(), length.getConvertedValue()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getConvertedValue()).toHashCode();
  }

  public enum Unit {

    CENTIMETER(0.01), METER(1.0), KILOMETER(1000);

    final double conversionFactor;

    Unit(double getConversionFactor) {
      this.conversionFactor = getConversionFactor;
    }

  }

}
