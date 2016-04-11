@Stateless
public class ApplicationUserBean {

	@PersistenceContext(unitName = "SZERPU")
	EntityManager entityManager;
	
	public void doRegistration(ApplicationUser newUser){
		entityManager.persist(newUser);
	}
	
	public ApplicationUser doLogin(LoginDTO loginDTO){
		ApplicationUser userFromDB;
		
		TypedQuery<ApplicationUser> loginQuery;
		loginQuery = entityManager.createQuery("SELECT au FROM ApplicationUser au" +
		" WHERE au.username=:un AND au.password=:p", ApplicationUser.class);
		loginQuery.setParameter("un", loginDTO.getUsername());
		loginQuery.setParameter("p", loginDTO.getPassword());
		
		try{
			userFromDB = loginQuery.getSingleResult();
		}
		catch(Exception e){
			userFromDB = null;
		}
		
		return userFromDB;
	}
	
	//...
}