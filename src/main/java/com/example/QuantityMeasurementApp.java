package com.example;

public class QuantityMeasurementApp 
{
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Feet feet3 = new Feet(2.0);

        System.out.println("Comparing 1.0 ft and 1.0 ft: " + feet1.equals(feet2));
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + feet1.equals(feet3));
        System.out.println("Comparing 1.0 ft with null: " + feet1.equals(null));
        System.out.println("Comparing 1.0 ft with itself: " + feet1.equals(feet1));
    }
}