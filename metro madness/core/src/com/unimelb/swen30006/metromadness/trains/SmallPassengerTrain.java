package com.unimelb.swen30006.metromadness.trains;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public class SmallPassengerTrain extends Train {
	
	public SmallPassengerTrain(Line trainLine, Station start, boolean forward,int size) {
		super(trainLine, start, forward,size);
	}

	@Override
	public void embark(Passenger p) throws Exception {
		if(this.passengers.size() > 10){
			throw new Exception();
		}
		this.passengers.add(p);
	}
	
	@Override
	public void render(ShapeRenderer renderer){
		if(!this.inTheStation()){
			Color col = this.forward ? FORWARD_COLOUR : BACKWARD_COLOUR;
			float percentage = this.passengers.size()/10f;
			renderer.setColor(col.cpy().lerp(Color.DARK_GRAY, percentage));
			// We also get slightly bigger with passengers
			renderer.circle(this.pos.x, this.pos.y, TRAIN_WIDTH*(1+percentage));
		}
	}

}
