public class Node
{
	public Node( String name, int duration ) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setDuration(duration);
	}

	public String getName( ) {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getDuration( ) {
		return duration;
	}

	public void setDuration( int duration ) {
		this.duration = duration;
	}

	private String name;
	
	private int duration;
	
	private Boolean on_critical_path;
	
	public String toString( ) {
		return getName() + " [shape=polygon, sides=4,label= \""+ getName()+" ("+ Integer.toString(getDuration()) +" days) \"]";
	}
	

}
