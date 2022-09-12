package com.appointments.system.utils;

@FunctionalInterface
public interface DataTraveler {
    // transfer data between 2 controller
    public void data(Object... o);
}
