package com.medline.tdd.day1.conversion;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Distance {

  private final double value;
  private final Unit unit;

  public Distance(double value, Unit unit) {
    this.value = value;
    this.unit = unit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Distance distance = (Distance) o;

    return new EqualsBuilder().append(convertToCentimeters(), distance.convertToCentimeters()).isEquals();
  }

  private double convertToCentimeters() {
    return unit.convertToCentimeters(value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(convertToCentimeters()).toHashCode();
  }

  public Distance addLength(Distance other) {
    double sum = value + unit.valueInUnit(other.convertToCentimeters(), unit);
    return new Distance(sum, unit);
  }

  public enum Unit {
    CENTIMETER(1),
    METER(CENTIMETER.conversionFactor * 100),
    KILOMETER(METER.conversionFactor * 1000);

    private final double conversionFactor;

    Unit(double getConversionFactor) {
      conversionFactor = getConversionFactor;
    }

    public double convertToCentimeters(double value) {
      return conversionFactor * value;
    }

    public double valueInUnit(double valueInCentimeters, Unit unit) {
      return valueInCentimeters / unit.conversionFactor;
    }

  }

}
