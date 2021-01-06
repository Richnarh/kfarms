/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Cart;
import com.khoders.kfms.entities.Inventory;
import com.khoders.kfms.entities.Product;
import com.khoders.kfms.entities.SalesCatalogue;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.InventoryService;
import com.khoders.kfms.entities.settings.Category;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.StringUtil;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "posController")
@SessionScoped
public class PosController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private InventoryService inventoryService;
    
    private Category category = new Category();
    private Inventory inventory = new Inventory();
    private Product product = new Product();
    private Cart cart = new Cart();
    
    private SalesCatalogue salesCatalogue;
    
    private List<Inventory> inventoryList = new LinkedList<>();
    
    private List<Cart> cartList;
    private List<SalesCatalogue> salesCatalogueList = new LinkedList<>();
    
    private double totalCostofPurchase = 0;
    private String receiptNumber,note;
    
    @PostConstruct
    private void init()
    {
        resetAllTransaction();
    }
    
    public void loadSaleItemsPerCategory()
    {
        inventoryList = inventoryService.getCategoryInventory(category);
    }
    
    public void loadCartList(SalesCatalogue salesCatalogue)
    {
        cartList = inventoryService.getCartList(salesCatalogue);
    }
    
    public void selectItem(Cart cart)
    {
        this.cart = cart;
    }
    
    public void addItemToCart()
    {
       if(cart.getInventory().getProduct() == null)
        {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please select a product"), null));
            return;
        }
       
        if(cart.getQuantity() <= 0)
        {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please enter a valid quantity!"), null));

            return;
        }
        
        if(cart.getInventory().getDiscount() <= 0.00)
        {
            cart.setSalesCatalogue(salesCatalogue);
            cart.setCostPrice(cart.getCostPrice());
            cart.setSellingPrice(cart.getInventory().getSellingPrice());
            
            cart.setTotalAmount(cart.getSellingPrice() * cart.getQuantity());

            if(StringUtil.isStringNullOrEmpty(cart.getId()))
            {
                cart.setId(crudApi.genId());
            }

            cartList = CollectionList.washList(cartList, cart);
            
            cart = new Cart(); 
            
        }
        else
        {
            double discount = cart.getInventory().getDiscount();
            
            double discountPrice = (discount/100) * cart.getInventory().getSellingPrice();
            
            double sellingPrice = discountPrice * cart.getQuantity();
            
            cart.setSalesCatalogue(salesCatalogue);
            cart.setCostPrice(cart.getCostPrice());
            cart.setSellingPrice(cart.getInventory().getSellingPrice());
            
            cart.setTotalAmount(sellingPrice);

            if(StringUtil.isStringNullOrEmpty(cart.getId()))
            {
                cart.setId(crudApi.genId());
            }
            
            cartList = CollectionList.washList(cartList, cart);

            cart = new Cart(); 
        }
    }
    
    public void editCart(Cart cart)
    {
        this.cart = cart;
    }
    public void removeFromCart(Cart cart)
    {
        cartList.remove(cart);
    }

    private boolean getCartTotal()
    {
        
        double amount = 0;
        try 
        {
            for (Cart cartItem : cartList) 
            {
                if (cartItem.getQuantity() > cartItem.getInventory().getQuantity()) 
                {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    Msg.setMsg("Limited stock for this transaction(s): "
                                            +cartItem.getInventory().getProduct().getProductName()), null));
                    return false;
                }
                int qtyPurchased = cartItem.getQuantity();
                int qtyAtInventory = cartItem.getInventory().getQuantity();

                int qtyAtHand = qtyAtInventory - qtyPurchased;
                
                 try 
                 {
                    inventory = crudApi.getEm().find(Inventory.class, cartItem.getInventory().getId());
                    inventory.setQuantity(qtyAtHand);
                    crudApi.save(inventory);

                }
                 catch (Exception e) 
                 {
                    e.printStackTrace();
                }
                
                amount += cartItem.getTotalAmount();
            }
            totalCostofPurchase = amount;
            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
   
    public void processTransaction()
    {
        if(cartList == null)
        {
             FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("The Cart is empty!"), null));
            return;
        }
        
       if(getCartTotal() == false)
       {
         return;
       }
        
        salesCatalogue.setPurchaseDate(LocalDate.now());
        inventoryService.generateReceipt(salesCatalogue);
        salesCatalogue.setTotalAmount(totalCostofPurchase);
        
        if(crudApi.save(salesCatalogue) != null)
        {
            cartList.stream().map(list -> 
            {
                list.genCode();
                list.setFarmAccount(appSession.getCurrentUser());
                return list;
            }).forEachOrdered(list ->
            {
                crudApi.save(list);
            });
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Failed to execute transaction"), null));
        }
        
    }
    
    public void getSalesByReceipt()
    {
        salesCatalogueList = inventoryService.getSalesRecord(receiptNumber);
    }
    
    public void resetAllTransaction()
    {
        cartList = new LinkedList<>();
        inventoryList = new LinkedList<>();
        salesCatalogueList = new LinkedList<>();
        totalCostofPurchase = 0.00;
        salesCatalogue = new SalesCatalogue();
        category = new Category();
        inventory = new Inventory();
        cart = new Cart();
        
        category.setFarmAccount(appSession.getCurrentUser());
        inventory.setFarmAccount(appSession.getCurrentUser());
        cart.setFarmAccount(appSession.getCurrentUser());
        salesCatalogue.setFarmAccount(appSession.getCurrentUser());
        
        SystemUtils.resetJsfUI();
    }
    
    public void manageSales(SalesCatalogue salesCatalogue)
    {
        this.salesCatalogue = salesCatalogue;
        
        cartList = inventoryService.getSalesList(salesCatalogue);
    }
    
    public Category getCategory() 
    {
        return category;
    }

    public void setCategory(Category category)
    {
       this.category = category;
    }

    public Product getProduct() 
    {
        return product;
    }

    public void setProduct(Product product) 
    {
        this.product = product;
    }

    public Cart getCart() 
    {
        return cart;
    }

    public void setCart(Cart cart) 
    {
        this.cart = cart;
    }

    public List<Cart> getCartList() 
    {
        return cartList;
    }

    public void setTotalCostofPurchase(double totalCostofPurchase) 
    {
        this.totalCostofPurchase = totalCostofPurchase;
    }

    public double getTotalCostofPurchase() 
    {
        return totalCostofPurchase;
    }

    public SalesCatalogue getSalesCatalogue() 
    {
        return salesCatalogue;
    }

    public void setSalesCatalogue(SalesCatalogue salesCatalogue) 
    {
        this.salesCatalogue = salesCatalogue;
    }

    public List<SalesCatalogue> getSalesCatalogueList() 
    {
        return salesCatalogueList;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) 
    {
        this.receiptNumber = receiptNumber;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    } 
}
