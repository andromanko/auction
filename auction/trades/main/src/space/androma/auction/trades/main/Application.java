package space.androma.auction.trades.main;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import space.androma.auction.trades.api.LotRepo;
import space.androma.auction.trades.api.UserRepo;
import space.androma.auction.trades.entity.User;

public class Application
{
        public static void main(String[] args) {

               /* try {

                        *//**** Connect to MongoDB ****//*
                        // Since 2.10.0, uses MongoClient
                        MongoClient mongo = new MongoClient("localhost", 27017);

                        *//**** Get database ****//*
                        // if database doesn't exists, MongoDB will create it for you
                   //     DB db = mongo.getDB("testdb");

*//*                        *//**//**** Get collection / table from 'testdb' ****//**//*
                        // if collection doesn't exists, MongoDB will create it for you
                        DBCollection table = db.getCollection("user");

                        *//**//**** Insert ****//**//*
                        // create a document to store key and value
                        BasicDBObject document = new BasicDBObject();
                        document.put("name", "mkyong");
                        document.put("age", 30);
                        document.put("createdDate", new Date());
                        table.insert(document);

                        *//**//**** Find and display ****//**//*
                        BasicDBObject searchQuery = new BasicDBObject();
                        searchQuery.put("name", "mkyong");

                        DBCursor cursor = table.find(searchQuery);

                        while (cursor.hasNext()) {
                                System.out.println(cursor.next());*//*
                        *//**** Done ****//*
                        System.out.println("Done");

                } catch (UnknownHostException e) {
                        e.printStackTrace();
                } catch (MongoException e) {
                        e.printStackTrace();
                }*/

                ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(
                new ClassPathResource("spring-config.xml").getPath());

                UserRepo userRepo = context.getBean(UserRepo.class);
                LotRepo lotRepo = context.getBean(LotRepo.class);


                User user = new User();
                user.setUserId(1l);
                user.setName("UserTest");
                userRepo.save(user);


        }
}