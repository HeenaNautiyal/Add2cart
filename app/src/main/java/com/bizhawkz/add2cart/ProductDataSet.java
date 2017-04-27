package com.bizhawkz.add2cart;

/**
 * Created by Heena on 3/30/2017.
 */
public interface ProductDataSet {

    int size();

    Product get(int position);

    long getId(int position);
}
