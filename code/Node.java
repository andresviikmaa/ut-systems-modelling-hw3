public class Node
{
	public Node( String name, int duration ) {
		this.setName(name);
		this.setDuration(duration);
		this.on_critical_path = false;

		
		
		
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
	private int EST;
	private int EFT;
	private int LST;
	private int LFT;
	private int slack;
	
	
	private Boolean on_critical_path;
	
	public String toString( ) {
		if (on_critical_path){
			return getName() + " [shape=polygon, color=\"red\", sides=4,label= \""+ getName()+" ("+ Integer.toString(getDuration()) +" days) \"]";
		}else{
			return getName() + " [shape=polygon, sides=4,label= \""+ getName()+" ("+ Integer.toString(getDuration()) +" days) \"]";
		}
	}

	public int getEFT( ) {
		return EFT;
	}

	public void setEFT( int eFT ) {
		EFT = eFT;
	}

	public int getEST( ) {
		return EST;
	}

	public void setEST( int eST ) {
		EST = eST;
	}

	public int getLST( ) {
		return LST;
	}

	public void setLST( int lST ) {
		LST = lST;
	}

	public int getLFT( ) {
		return LFT;
	}

	public void setLFT( int lFT ) {
		LFT = lFT;
	}

	public int getSlack( ) {
		return slack;
	}

	public void setSlack( int slack ) {
		this.slack = slack;
	}

	public Boolean getOn_critical_path( ) {
		return on_critical_path;
	}

	public void setOn_critical_path( Boolean on_critical_path ) {
		this.on_critical_path = on_critical_path;
	}
	

}
