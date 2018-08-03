package hack.hajj.com.hajj_hack;

/**
 * Created by hanin_5p on 02/08/18.
 */

class Order {
    private String number , evaluation ,date, time ,orderstate;

    public  Order(){
    }

    public Order(String number , String evaluation ,String date , String time){
        this.number = number;
        this.evaluation = evaluation;
        this.date =date ;
        this.time =time ;
    }
    public Order(String number , String evaluation ,String orderstate ){
        this.number = number;
        this.evaluation = evaluation;
        this.orderstate =orderstate ;
    }

    public String getNumber(){
        return number;     
    }
    public String getEvaluation(){
        return evaluation;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getState() { return orderstate; }
}
