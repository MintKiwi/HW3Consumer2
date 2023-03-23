import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;


public class MessageDispatcher implements Runnable {

    private Connection connection;


    public MessageDispatcher(Connection connection) {

        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            Channel channel = connection.createChannel();
            channel.basicConsume("direct2", true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    ConcurrentHashMap<String, HashSet<String>> map = Consumer.map;
                    int index = message.indexOf("swiper");
                    int index2 = message.indexOf("swipee");
                    int index3 = message.indexOf("comment");
                    String swiper = message.substring(index + 9, index2 - 3);
                    int index4 = message.indexOf("leftOrRight");
                    String direction = message.charAt(index4 + 14) == 'r' ? "right" : "left";
                    if (direction.equals("right")) {
                        if (!map.containsKey(swiper)) {
                            map.put(swiper, new HashSet<>(100));
                        }
                        if (map.get(swiper).size() < 100) {
                            map.get(swiper).add(message.substring(index2 + 9, index3 - 3));
                        }

                    }
                }

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
