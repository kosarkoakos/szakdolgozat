@Stateful
public class TableContentHandlerBean {

	@PersistenceContext(unitName = "SZERPU")
	EntityManager entityManager;
	
	public IndexedContainer makeTelephoneIndexedConatiner(){
		IndexedContainer ic=new IndexedContainer();
		
		ic.addContainerProperty("Név", String.class, null);
		ic.addContainerProperty("Leírás", String.class,null);
        ic.addContainerProperty("Hűségidő", Integer.class,null);
        ic.addContainerProperty("Ár", Integer.class, null);
        ic.addContainerProperty("Típus", String.class,null);
		
		ArrayList<TelephoneService> allTelephoneServices;
		TypedQuery<TelephoneService> query=entityManager.createQuery("SELECT s FROM TelephoneService s",TelephoneService.class);
		allTelephoneServices=(ArrayList<TelephoneService>) query.getResultList();
		
		for(TelephoneService ts : allTelephoneServices){
			Item item= ic.addItem(ts);
            item.getItemProperty("Név").setValue(ts.getName());
            item.getItemProperty("Leírás").setValue(ts.getDescription());
            item.getItemProperty("Hűségidő").setValue(ts.getLoyalty());
            item.getItemProperty("Ár").setValue(ts.getPrice());
            item.getItemProperty("Típus").setValue(ts.getType());
		}
		
		return ic;
	}	
	//...
}