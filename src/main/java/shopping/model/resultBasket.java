package shopping.model;

import java.util.List;

public class resultBasket {
		long id ;
		private List<resultBasketItem> basketItem ; 
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public List<resultBasketItem> getBasketItem() {
			return basketItem;
		}

		public void setBasketItem(List<resultBasketItem> basketItem) {
			this.basketItem = basketItem;
		}
		
		
		
	}

