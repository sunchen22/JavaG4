package com.grouporder.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.dinerinfo.entity.DinerInfo;
import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;
import com.producttype.entity.ProductType;
import com.productvary.entity.ProductVary;
import com.varytype.entity.VaryType;
import com.buildinginfo.entity.BuildingInfo;

public class GroupOrderDAOHibernateImpl implements GroupOrderDAO {
	private static final int PAGE_MAX_RESULT = 3;
	// One SessionFactory(which is thread-safe) for one DAO
	private SessionFactory factory;

	public GroupOrderDAOHibernateImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Each CRUD method in this DAO should get its own Session(which is not
	// thread-safe)
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(GroupOrder groupOrder) {
		try {
//			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(groupOrder);
//			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public int update(GroupOrder groupOrder) {
		try {
//			getSession().beginTransaction();
			getSession().update(groupOrder);
//			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public int delete(Integer groupOrderID) {
		try {
//			getSession().beginTransaction();
			GroupOrder groupOrder = getSession().get(GroupOrder.class, groupOrderID);
			if (groupOrder != null) {
				getSession().delete(groupOrder);
			}
//			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public GroupOrder findByPK(Integer groupOrderID) {
		try {
//			getSession().beginTransaction();
			GroupOrder groupOrder = getSession().get(GroupOrder.class, groupOrderID);
//			getSession().getTransaction().commit();

			return groupOrder;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
	@Override
	public DinerInfo findByPKJoinDiner(Integer groupOrderID) {
		try {
//			getSession().beginTransaction();
			DinerInfo dinerInfo = getSession().get(GroupOrder.class, groupOrderID).getDinerInfo();
//			getSession().getTransaction().commit();
			return dinerInfo;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}

	@Override
	public Product findByPKProduct(Integer productID) {
		try {
//			getSession().beginTransaction();
			Product product = getSession().get(Product.class, productID);
//			getSession().getTransaction().commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		
		return null;
	}	
	
	@Override
	public ProductVary findByPKProductVary(Integer productVaryID) {
		try {
//			getSession().beginTransaction();
			ProductVary productVary = getSession().get(ProductVary.class, productVaryID);
//			getSession().getTransaction().commit();
			return productVary;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		
		return null;
	}	

	@Override
	public DinerInfo findByPKDiner(Integer dinerID) {
		try {
//			getSession().beginTransaction();
			DinerInfo dinerInfo = getSession().get(DinerInfo.class, dinerID);
//			getSession().getTransaction().commit();
			return dinerInfo;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		
		return null;
	}
	
	@Override
	public BuildingInfo findByPKBuilding(Integer buildingID) {
		try {
//			getSession().beginTransaction();
			BuildingInfo buildingInfo = getSession().get(BuildingInfo.class, buildingID);
//			getSession().getTransaction().commit();
			return buildingInfo;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		
		return null;
	}	
	
	@Override
	public List<GroupOrder> getAll() {
		try {
//			getSession().beginTransaction();
			List<GroupOrder> list = getSession().createQuery("from GroupOrder", GroupOrder.class).list();
//			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
	@Override
	public List<GroupOrder> getAllStatusOneTwo() {
		try {
//			getSession().beginTransaction();
			List<GroupOrder> list = getSession().createQuery("from GroupOrder where orderStatus = '1' OR orderStatus = '2'", GroupOrder.class).list();
//			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<Object[]> getAllJoin(Integer currentPage) {
		try {
//			getSession().beginTransaction();
			String hql = "SELECT go, d.dinerID, d.dinerName, d.dinerAddress, d.dinerType, d.dinerOrderThreshold, d.dinerStatus, "
					+ "b.buildingName, b.buildingAddress, u.userNickName, "
					+ "(SELECT ROUND(AVG(drc.dinerRating), 1) FROM DinerRatingComment drc WHERE drc.dinerInfo = d) AS averageRating "
					+ "FROM GroupOrder go " 
					+ "LEFT JOIN go.dinerInfo d " + "LEFT JOIN go.buildingInfo b "
					+ "LEFT JOIN go.userInfo u " 
					+ "WHERE go.orderStatus = '1' OR go.orderStatus = '2'";
//			分頁
//			int first = (currentPage - 1) * PAGE_MAX_RESULT;
//			List<Object[]> results = getSession().createQuery(hql, Object[].class).setFirstResult(first).setMaxResults(PAGE_MAX_RESULT)
//					.list();
//			分頁
			
			List<Object[]> results = getSession().createQuery(hql, Object[].class).list();
//			getSession().getTransaction().commit();
			return results;

		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<Object[]> getOneJoin(Integer groupOrderID) {
		try {
//			getSession().beginTransaction();
			String hql = "SELECT go, d.dinerID, d.dinerName, d.dinerAddress, d.dinerType, d.dinerOrderThreshold, d.dinerStatus, "
					+ "b.buildingName, b.buildingAddress, u.userNickName, "
					+ "(SELECT ROUND(AVG(drc.dinerRating), 1) FROM DinerRatingComment drc WHERE drc.dinerInfo = d) AS averageRating "
					+ "FROM GroupOrder go " 
					+ "LEFT JOIN go.dinerInfo d " + "LEFT JOIN go.buildingInfo b "
					+ "LEFT JOIN go.userInfo u " 
					+ "WHERE go.groupOrderID = " 
					+ String.valueOf(groupOrderID);

			List<Object[]> result = getSession().createQuery(hql, Object[].class).list();
//			getSession().getTransaction().commit();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
	@Override
	public List<Object[]> getOneJoinMenu(Integer dinerID) {
		try {
//			getSession().beginTransaction();
			String sql = "SELECT p.productID, p.productName, p.productPrice, pt.productTypeDes "
					+ "	FROM DinerInfo AS d "
					+ "	LEFT JOIN Product AS p ON d.dinerID = p.dinerID "
					+ "	LEFT JOIN ProductType AS pt ON p.productTypeID = pt.productTypeID "
					+ "	WHERE d.dinerID = " + String.valueOf(dinerID) + " AND p.productStatus = '上架中' "
					+ "	ORDER BY pt.productTypeID, p.productPrice";	
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = getSession().createNativeQuery(sql).list();
//			getSession().getTransaction().commit();

			return results;

		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
	@Override
	public List<Object[]> getProductJoinProductVary(Integer productID) {
		try {
//			getSession().beginTransaction();
			String sql = "SELECT pv.productvaryID, pv.productVaryDes, pv.productVaryPrice, vt.varyType "
					+ "	FROM Product AS p "
					+ "	LEFT JOIN ProductType AS pt ON p.productTypeID = pt.productTypeID "
					+ "	LEFT JOIN ProductVary AS pv ON p.productID = pv.productID "
					+ "	LEFT JOIN VaryType AS vt ON pv.varyTypeID = vt.varyTypeID "
					+ "	WHERE p.productID = " + String.valueOf(productID)
					+ "	ORDER BY pv.varyTypeID, pv.productVaryPrice";	
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = getSession().createNativeQuery(sql).list();
//			getSession().getTransaction().commit();

			return results;

		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		return null;		
	}
	
	@Override
	public List<Object[]> getGroupOrderByKeywords(String nameKeyword, String addressKeyword) {
		try {
//			getSession().beginTransaction();
			StringBuilder sql = new StringBuilder("SELECT go.groupOrderID, go.dinerID, go.buildingID, go.orderStatus, go.groupOrderCreateTime, go.groupOrderSubmitTime, go.holderID, go.groupTotalPrice, d.dinerName, d.dinerAddress, d.dinerType, d.dinerOrderThreshold, d.dinerStatus, "
					+ "b.buildingName, b.buildingAddress, u.userNickName, "
					+ "(SELECT ROUND(AVG(drc.dinerRating), 1) FROM DinerRatingComment drc WHERE drc.dinerID = go.dinerID) AS dinerRating "
					+ "FROM GroupOrder go "
	                + "LEFT JOIN DinerInfo AS d ON go.dinerID = d.dinerID "
	                + "LEFT JOIN BuildingInfo AS b on go.buildingID = b.buildingID "
	                + "LEFT JOIN UserInfo AS u ON go.holderID = u.userID "
	                + "WHERE (go.orderStatus = 1 OR go.orderStatus = 2) ");

			nameKeyword = (nameKeyword != null) ? nameKeyword.trim() : null;
	        addressKeyword = (addressKeyword != null) ? addressKeyword.trim() : null;
			
	        // Use parameters for LIKE conditions
	        if (nameKeyword != null && !nameKeyword.isEmpty()) {
	            sql.append(" AND d.dinerName LIKE :name");
	        }
	        if (addressKeyword != null && !addressKeyword.isEmpty()) {
	            sql.append(" AND b.buildingAddress LIKE :address");
	        }

	        NativeQuery<Object[]> query = getSession().createNativeQuery(sql.toString());

	        // Set parameters for LIKE conditions
	        if (nameKeyword != null && !nameKeyword.isEmpty()) {
	            query.setParameter("name", "%" + nameKeyword + "%");
	        }
	        if (addressKeyword != null && !addressKeyword.isEmpty()) {
	            query.setParameter("address", "%" + addressKeyword + "%");
	        }

	        List<Object[]> results = query.getResultList();
					
					
//			getSession().getTransaction().commit();
			return results;

		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		return null;	
	}
}
