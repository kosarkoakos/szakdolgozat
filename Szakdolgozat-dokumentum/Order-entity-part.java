@Entity
@Table(name="Orders")
public class Order {
	//...

	@ManyToMany
	@JoinTable(name = "JND_ORDERS_SERVICES",
	joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
	inverseJoinColumns = @JoinColumn(name = "SERVICE_ID_FK"))
	private List<Service> services;

	//...
}