package org.academiacodigo.bootcamp.vascos.circulargame.model.game_objects;

import org.academiacodigo.bootcamp.vascos.circulargame.model.ModelGame;


public class Launcher {
    private int playerId;
    private LilBall nextBallToSpit;
    private ModelGame modelGame;


    public Launcher(int playerId, ModelGame modelGame) {
        this.modelGame = modelGame;
        this.playerId = playerId;
        this.nextBallToSpit = getNewLilBall();

    }

    public LilBall getNextBallToSpit() {
        return nextBallToSpit;
    }

    private LilBall getNewLilBall() {
        //get new lilBall
        LilBall lilBall = LilBallFactory.getNewLilBall(playerId);
        //warn model so it can warn controller
        modelGame.publish(GameObjectType.LILBALL, lilBall);
        lilBall.registerSubscriber(modelGame.getBigBall());
        return lilBall;
    }

    public void launch() {
        nextBallToSpit.startMoving();
        nextBallToSpit = getNewLilBall();
    }

}