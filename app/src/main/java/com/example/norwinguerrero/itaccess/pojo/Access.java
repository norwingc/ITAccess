package com.example.norwinguerrero.itaccess.pojo;

import android.graphics.Bitmap;

/**
 * Created by Norwin Guerrero on 2/2/2016.
 */
public class Access {

    private int _id;
    private String _date;
    private String _fullname;
    private String _company;
    private String _purposeofvisit;
    private String _signature;
    private String _timein;
    private String _timeout;
    private String _escort;
    private byte[] _photo;

    public Access() {
    }

    public Access(int _id, String _date, String _fullname, String _company, String _purposeofvisit, String _timein, String _signature, String _escort, String _timeout, byte[] _photo) {
        this._id = _id;
        this._date = _date;
        this._fullname = _fullname;
        this._company = _company;
        this._purposeofvisit = _purposeofvisit;
        this._timein = _timein;
        this._signature = _signature;
        this._escort = _escort;
        this._timeout = _timeout;
    }

    public Access(String _date, String _fullname, String _company, String _purposeofvisit, String _signature, String _timein, String _timeout, String _escort) {
        this._date = _date;
        this._fullname = _fullname;
        this._company = _company;
        this._purposeofvisit = _purposeofvisit;
        this._signature = _signature;
        this._timein = _timein;
        this._timeout = _timeout;
        this._escort = _escort;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_fullname() {
        return _fullname;
    }

    public void set_fullname(String _fullname) {
        this._fullname = _fullname;
    }

    public String get_company() {
        return _company;
    }

    public void set_company(String _company) {
        this._company = _company;
    }

    public String get_purposeofvisit() {
        return _purposeofvisit;
    }

    public void set_purposeofvisit(String _purposeofvisit) { this._purposeofvisit = _purposeofvisit; }

    public String get_signature() {
        return _signature;
    }

    public void set_signature(String _signature) {
        this._signature = _signature;
    }

    public String get_timein() {
        return _timein;
    }

    public void set_timein(String _timein) {
        this._timein = _timein;
    }

    public String get_timeout() {
        return _timeout;
    }

    public void set_timeout(String _timeout) {
        this._timeout = _timeout;
    }

    public String get_escort() {
        return _escort;
    }

    public void set_escort(String _escort) {
        this._escort = _escort;
    }

    public byte[] get_photo() {
        return _photo;
    }

    public void set_photo(byte[] _photo) {
        this._photo = _photo;
    }
}
