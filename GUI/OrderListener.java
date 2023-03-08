public interface OrderListener {
    public void itemAdded(String itemMenuName);
    public void itemRemoved(String itemMenuName);
    public void orderCompleted();
}
