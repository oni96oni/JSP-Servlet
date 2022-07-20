package common.service;

public abstract class AbstractService {

	public abstract Object getAll();
	public abstract Object getId(Object o);
	public abstract Object getId(int i);
	public abstract boolean add(Object o);
	public abstract boolean modify(Object o);
	public abstract boolean remove(Object o);
	public abstract boolean remove(int i);
	
}
