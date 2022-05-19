package io.taech.graph;

import java.util.Arrays;

public class Network {

    private int no;
    private int[] networks;

    Network(int no, int[] networks) {
        this.no = no;
        this.networks = networks;
    }

    int getNo() {
        return this.no;
    }

    int[] getNetworks() {
        return this.networks;
    }

    @Override
    public String toString() {
        return String.format("{\"no\": %d, \"networks\": %s}", this.no, Arrays.toString(this.networks));
    }
}

