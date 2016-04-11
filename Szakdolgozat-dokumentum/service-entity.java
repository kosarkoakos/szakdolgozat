@Entity
@Table(name="SERVICES")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "SERVICETYPE")
public class Service implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="SERVICE_ID")
	private Long serviceId;

	@Column(name="NAME", nullable = false, length = 99)
	private String name;

	@Column(name="PRICE", nullable = false)
	private Integer price;

	@Column(name="DESCRIPTION", nullable = false, length = 255)
	private String description;

	@ManyToMany(mappedBy = "parts")
	private List<ServicePack> containerServicePacks;

	@ManyToMany(mappedBy = "services")
	private List<Order> containerOrders;

	@Column(name="LOYALTY", nullable = false)
	private int loyalty;

	public Service(){

	}

	//getterek, setterek, további konstruktorok
}