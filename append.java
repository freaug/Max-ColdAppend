import com.cycling74.max.*;

public class append extends MaxObject
{
	public Atom[] stuff = null;
	public Atom[] append = null;

	public append(Atom[] args){
		//declaring inlets
		declareInlets(new int[]{DataTypes.ALL, DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		createInfoOutlet(false);
		//help messages
		setInletAssist(new String[]{"Message to append", "Stored message"});
		setOutletAssist(new String[]{"Appended message"});
	}

	//concat function
	public Atom[] concat(Atom[] a, Atom[] b){
		//make sure we don't get a nullpointerexception
		if(b==null){
			return a;
		}
		int aLen = a.length;
		int bLen = b.length;
		Atom[] c = new Atom[aLen+bLen];
		System.arraycopy(b, 0, c, 0, bLen);
		System.arraycopy(a, 0, c, bLen, aLen);
	//	System.arraycopy(b, 0, c, aLen, bLen);
	//	System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
	//add second inlet messages to stuff
	private void setStuff(Atom[] a){
		stuff = a;
	}
	//add first inlet messages to prepend
	private void setAppend(Atom[] a){
		append = a;
	}
	//add's int's to prepend or stuff if prepend outputs the complete message
	public void inlet(int i){
		if(getInlet()==0){
			setAppend(new Atom[] {Atom.newAtom(i)});
			outlet(0, concat(append, stuff));
		}else{
			setStuff(new Atom[] {Atom.newAtom(i)});
		}	
	}
    //add's float's to prepend or stuff if prepend outputs the complete message
	public void inlet(float f){
		if(getInlet()==0){
			setAppend(new Atom[] {Atom.newAtom(f)});
			outlet(0, concat(append, stuff));
		}else{
			setStuff(new Atom[] {Atom.newAtom(f)});
		}

	}
	//add lists to prepend or stuff if prepend outputs the complete message
	public void list(Atom[] a){
		if(getInlet()==0){
			setAppend(a);
			outlet(0, concat(append, stuff));
		}else{
			setStuff(a);
		}
	}
	//add's anything else to prepend or stuff if prepend outputs the complete message
	public void anything(String msg, Atom[] a){
		if(getInlet()==0){
			setAppend(Atom.newAtom(msg, a));
			outlet(0, concat(append, stuff));
		}else{
			setStuff(Atom.newAtom(msg, a));
		}			
	}
}









