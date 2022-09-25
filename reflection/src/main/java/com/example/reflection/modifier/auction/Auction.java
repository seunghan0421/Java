package com.example.reflection.modifier.auction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Auction implements Serializable {
	private final List<Bid> bids = new ArrayList<>();

	private transient volatile boolean isAuctionStarted;

	// Methods
	public synchronized void addBid(Bid bid) {
		this.bids.add(bid);
	}

	public synchronized List<Bid> getAllBids() {
		return Collections.unmodifiableList(bids);
	}

	public synchronized Optional<Bid> getHighestBid() {
		return bids.stream()
			.max(Comparator.comparing(Bid::getPrice));
	}

	public void startAuction() {
		this.isAuctionStarted = true;
	}

	public void stopAuction() {
		this.isAuctionStarted = false;
	}

	public boolean isAuctionRunning() {
		return isAuctionStarted;
	}

}
