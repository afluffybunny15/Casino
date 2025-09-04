import React from 'react';

const HomepageComponent = () => {
    return (
        <div className="Homepage">
            <h1>Welcome to the Casino</h1>
            <p>Enjoy your stay and have fun!</p>
            <div className="links">
                <a href="/poker">Poker</a>
                <br />
                <a href="/blacjack">Blackjack</a>
            </div>
        </div>
    );
}

export default HomepageComponent;