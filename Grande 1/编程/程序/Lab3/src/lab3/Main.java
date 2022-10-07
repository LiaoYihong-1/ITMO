package lab3;

public class Main {
    public static void main(String [] args){
        CR ChristopherRobin=new CR("ChristopherRobin",30,Sex.MALE);
        Member Tom=new Member("Tom",22,Sex.MALE);
        Member Martha=new Member("Martha",25,Sex.FEMALE);
        River river=new River("river");
        Rockbeach rockbeach=new Rockbeach("rockbeach");
        Stranger stranger=new Stranger();

        rockbeach.membercome(ChristopherRobin);
        ChristopherRobin.changeStatus(Status.WAITING);
        ChristopherRobin.detailmove();
        rockbeach.membercome(stranger);
        stranger.detailmove();
        rockbeach.membercome(Martha);
        Martha.changeStatus(Status.WAITING);
        Martha.detailmove();
        rockbeach.membercome(Tom);

        rockbeach.meet();
        rockbeach.moveto(river);

        ChristopherRobin.changeStatus(Status.WALKING);
        Martha.changeStatus(Status.WALKING);
        Tom.changeStatus(Status.WALKING);
        ChristopherRobin.setDestname(river.placename);
        Martha.setDestname(river.placename);
        Tom.setDestname(river.placename);
        Tom.detailmove();
        ChristopherRobin.detailmove();
        Martha.detailmove();

        rockbeach.arriveat(river);

        ChristopherRobin.changeStatus(Status.STANDINGBYRIVER);
        Martha.changeStatus(Status.STANDINGBYRIVER);
        Martha.changeStatus(Status.STANDINGBYRIVER);
        ChristopherRobin.detailmove();
        ChristopherRobin.getSecialexperiment();
        ChristopherRobin.say("\"Guess what,I once fell from a tree and fell into a bush.The i pulled out all the thorns from me for week\"\n");
        Tom.say("\"Must be hurt\"\n");
        //System.out.print(rockbeach.hashCode());
        //System.out.print(ChristopherRobin.equals(Martha));
    }
}
