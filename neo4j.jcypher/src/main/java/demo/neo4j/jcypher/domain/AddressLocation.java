package demo.neo4j.jcypher.domain;

public class AddressLocation
{
	private String type;
	private double[][] coordinates;

	public AddressLocation()
	{
		super();
	}

	public AddressLocation(String type, double[][] coordinates)
	{
		super();
		this.type = type;
		this.coordinates = coordinates;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public double[][] getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(double[][] coordinates)
	{
		this.coordinates = coordinates;
	}
}
