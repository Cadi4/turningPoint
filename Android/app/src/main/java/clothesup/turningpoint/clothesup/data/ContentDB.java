package clothesup.turningpoint.clothesup.data;

import java.util.ArrayList;

/**
 * Created by bro. Jo on 2016-05-06.
 */
public class ContentDB {
    private String id;
    private ArrayList<String> name;
    public Info info;
    public Location location;

    public ContentDB(String id, ArrayList<String> name, Info info, Location location) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.location = location;
        //make info to class
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public Info getInfo() {
        return info;
    }

    public Location getLocation() {
        return location;
    }


    @Override
    public String toString() {
        return "ContentDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public class Info {
        private String goods;
        private ArrayList<String> kind;
        private ArrayList<String> style;
        private String price;
        private int card;
        private int cash;
        private int refund;
        private int exchange;
        private int fitting;
        private int open;
        private int close;
        private ArrayList<String> holiday;
        private String image;
        private float score;
        private int countN;

        public Info(ArrayList<String> kind, ArrayList<String> style, String price,
                    int card, int cash, int refund, int exchange, int fitting,
                    int open, int close, ArrayList<String> holiday, String image,
                    float score, int countN) {
            this.kind = kind;
            this.style = style;
            this.price = price;
            this.card = card;
            this.cash = cash;
            this.refund = refund;
            this.exchange = exchange;
            this.fitting = fitting;
            this.open = open;
            this.close = close;
            this.holiday = holiday;
            this.image = image;
            this.score = score;
            this.countN = countN;
        }

        public String getGoods() {
            return goods;
        }

        public ArrayList<String> getKind() {
            return kind;
        }

        public ArrayList<String> getStyle() {
            return style;
        }

        public String getPrice() {
            return price;
        }

        public int isCard() {
            return card;
        }

        public int isCash() {
            return cash;
        }

        public int isRefund() {
            return refund;
        }

        public int isExchange() {
            return exchange;
        }

        public int isFitting() {
            return fitting;
        }

        public int getOpen() {
            return open;
        }

        public int getClose() {
            return close;
        }

        public ArrayList<String> getHoliday() {
            return holiday;
        }

        public String getImage() {
            return image;
        }

        public float getScore() {
            return score;
        }

        public int getCountN() { return countN; }


        @Override
        public String toString() {
            return "Info{" +
                    "goods='" + goods + '\'' +
                    ", kind='" + kind + '\'' +
                    ", style='" + style + '\'' +
                    ", price='" + price + '\'' +
                    ", card=" + card +
                    ", refund=" + refund +
                    ", exchange=" + exchange +
                    ", fitting=" + fitting +
                    ", open=" + open +
                    ", close=" + close +
                    ", holiday=" + holiday +
                    ", image='" + image + '\'' +
                    ", score=" + score +
                    ", countN=" + countN + '\'' +
                    '}';
        }
    }

    public class Location {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Location(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    '}';
        }
    }
}


