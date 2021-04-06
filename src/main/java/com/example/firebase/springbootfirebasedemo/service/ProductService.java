package com.example.firebase.springbootfirebasedemo.service;

import com.example.firebase.springbootfirebasedemo.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTION_NAME ="products" ;

    public String saveProduct(Product product) throws ExecutionException, InterruptedException {

       Firestore dbFirestore= FirestoreClient.getFirestore();

       ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);

       return collectionApiFuture.get().getUpdateTime().toString();

    }

    public Product getProductDetailsByname(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document=future.get();

        Product product=null;
        if(document.exists()) {
         product = document.toObject(Product.class);
       return  product;
        }else{
            return null;
        }


    }

    public List<Product> getProductDetails() throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference=dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator=documentReference.iterator();

        List<Product> productList=new ArrayList<>();
        Product product=null;

        while(iterator.hasNext()){
            DocumentReference documentReference1=iterator.next();
           ApiFuture<DocumentSnapshot> future= documentReference1.get();
           DocumentSnapshot document=future.get();

            product=document.toObject(Product.class);
           productList.add(product);

        }
        return productList;
    }


    public String updateProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String deleteProduct(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(name).delete();

        return "Document with Product ID"+name+" has been deleted successfully";

    }
}
