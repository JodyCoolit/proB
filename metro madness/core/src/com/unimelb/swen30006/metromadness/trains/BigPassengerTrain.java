package com.unimelb.swen30006.metromadness.trains;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.ShortPlatformStation;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train.State;

public class BigPassengerTrain extends Train {
	
	public final int size = 80;

	public BigPassengerTrain(Line trainLine, Station start, boolean forward) {
		super(trainLine, start, forward);
	}
	
	public void onRoute(float delta) {
		// Checkout if we have reached the new station
		try {
			if(this.pos.distance(this.station.position) < 10){
				if(this.station instanceof ShortPlatformStation){
					this.state = State.PASSING_BY;
				}else{
					this.state = State.WAITING_ENTRY;
				}
			} else {
				move(delta);
			}	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void embark(Passenger p) throws Exception {
		if(this.passengers.size() > 80){
			throw new Exception();
		}
		this.passengers.add(p);
	}
	
	@Override
	public void render(ShapeRenderer renderer){
		if(!this.inTheStation()){
			Color col = this.forward ? FORWARD_COLOUR : BACKWARD_COLOUR;
			float percentage = this.passengers.size()/20f;
			renderer.setColor(col.cpy().lerp(Color.DARK_GRAY, percentage));
			renderer.circle(this.pos.x, this.pos.y, TRAIN_WIDTH*(1+percentage));
		}
	}

}
