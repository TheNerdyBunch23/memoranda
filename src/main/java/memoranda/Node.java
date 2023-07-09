package main.java.memoranda;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int coordinateX;
    private int coordinateY;
	private String id;
	private String stopName;
	private double latitude;
	private double longitude;
    private int x;
    private int y;
    private boolean isBusStop;
    private List<Node> neighbors;


	/**
     * The default constructor for Node
     *
     * @param id  the ID associated with the specific node
     * @param latitude the latitude coordinates of the Node
     * @param longitude the longitude coordinates of the Node
     */
    public Node(String id, double latitude, double longitude, boolean isBusStop, String stopName) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        coordinateX = -1;
        coordinateY = -1;
        this.isBusStop = isBusStop;
        this.stopName = stopName;
        x = -1;
        y = -1;
        neighbors = new ArrayList<>();
    }

    /**
     * Getter function for the String ID
     *
     * @return the ID associated with the node
     */
    public String getId() {
        return id;
    }

    /**
     * Getter function for the latitude value
     *
     * @return the latitude value of the Node
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter function for the longitude value
     *
     * @return the longitude value of the Node
     */
    public double getLongitude() {
        return longitude;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }


    /**
     * Calculates the distance between the Nodes in km
     *
     * @param n  the first Node
     * @param nn the second Node
     * @return the distance of the Nodes in km
     */
    public static double distanceOfNodes(Node n, Node nn) {
//        double R = 6371; // radius of the earth in km;
//        double dLat = Math.toRadians(n.getLatitude() - nn.getLatitude());
//        double dLon = Math.toRadians(n.getLongitude() - nn.getLongitude());
//
//        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
//                Math.cos(Math.toRadians(nn.getLatitude())) * Math.cos(Math.toRadians(n.getLatitude())) *
//                        Math.sin(dLon/2) * Math.sin(dLon/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double d = R * c; //distance in kilometers
//        return d;
        double xDiff = nn.getCoordinateX() - n.getCoordinateX();
        double yDiff = nn.getCoordinateY() - n.getCoordinateY();

        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return distance;
    }

    
    public boolean isBusStop() {
		return isBusStop;
	}
    
    public List<Node> getNeighbors() {
        return neighbors;

    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }
    
    public String getStopName() {
    	return stopName;
    }

    /**
     * toString implementation:
     * (unique ID number of Node)
     * latitude: (latitude of Node)
     * longitude: (longitude of Node)
     *
     * @return the string
     */
    public String toString() {
        return "Node: " + id + "\nlatitude: " + latitude + "\nlongitude: " + longitude;
    }

    /**
     * equals implementation
     *
     * @param n the comparison Node
     * @return returns true if the latitude and longitude values are equal, else returns false
     */
    public boolean equals(Node n) {
        return latitude == n.getLatitude() && longitude == n.getLongitude();
    }
}
