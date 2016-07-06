package com.example.lucas.myapp;

/**
 * Created by lrodrigues on 06/07/2016.
 */
public class Contact {

    private String _name, _phone, _email, _address;

    public Contact(String name, String phone, String email, String address) {
        _name = name;
        _phone = phone;
        _email = email;
        _address = address;
    }

    public String get_name() {
        return _name;
    }

    public String get_phone() {
        return _phone;
    }

    public String get_email() {
        return _email;
    }

    public String get_address() {
        return _address;
    }
}
