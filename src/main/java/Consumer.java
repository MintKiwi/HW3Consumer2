import com.rabbitmq.client.*;
import dao.SwipeDao;
import model.SwipePOJO;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer {

    //store match sets, two sets are required to avoid ConcurrentModificationException
    public static final Set<SwipePOJO> matchSet1 = Collections.synchronizedSet(new HashSet<SwipePOJO>());
    public static final Set<SwipePOJO> matchSet2 = Collections.synchronizedSet(new HashSet<SwipePOJO>());


    //record request count
    private static AtomicInteger count = new AtomicInteger(0);
    final static private int NUMTHREADS = 200;
    //rabbitmq host
    private static String host = "54.212.63.10";



    public AtomicInteger getCount() {
        return count;
    }


    public static void main(String[] args) throws Exception {

        Consumer consumer = new Consumer();
        Connection connection = Consumer.getConnection();
        ExecutorService pool = Executors.newFixedThreadPool(NUMTHREADS);
        for (int i = 0; i < NUMTHREADS; i++) {
            pool.execute(new MessageDispatcher(connection, consumer));

        }




    }


    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        factory.setPort(5672);

        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        return connection;
    }

}

