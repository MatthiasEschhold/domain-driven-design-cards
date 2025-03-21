# The Bounded Context Game

## Objective of the Game

The objective of the Bounded Context Game is to identify and describe Bounded Context candidates based on known heuristics.

Heuristics for the determination of bounded context boundaries are:

* Business Capabilities
* Language and model differences
* Unidirectional information flow
* Cohesive grouping of activities (processes, workflows, and use cases)
* Different triggers
* Independent creation of a result
* Teams and locations

### The Mission

Identifying and describing a Bounded Context represents a mission for the team. 
The mission is accomplished when the Bounded Context is described to the minimum 
extent using the [Bounded Context Canvas](https://github.com/ddd-crew/bounded-context-canvas) by the [DDD-Crew](https://github.com/ddd-crew).

The minimum extent includes:
* Purpose, responsibilities, reason for existence
* Applied heuristics for the boundary
* Key domain events, domain objects and business rules

The game consists of multiple missions and ends when all bounded contexts 
of the business domain or subdomain are defined.

## Game Preparation

To play the Bounded Context Game, visualized knowledge about the business domain is needed.

This game basis is created as the result of domain discovery through collaborative modeling. 
Based on our experience, suitable visualizations of domain knowledge could be:
* Business Capability Map
* Event Storm with Domain Events, Commands, Read Models and Policies
* Domain Stories
* Customer Journeys
* Business process models
* Examples and rules derived from Example Mapping

However, a combination of the aforementioned artifacts are also common.

Metaphorically or physically, these models represent the playground of the Bounded Context Game.

## Game Progress

> 1. Sharing Knowledge (30 to 90 minutes)

The moderator presents the artifacts from domain discovery and explains the requirements 
and challenges of the business domain. The players have the opportunity to ask questions and 
engage with the artifacts.

> 2. Discussion and Analysis (30 to 60 minutes)

In the next phase of the game, each player individually or in discussion with other 
players engages with the business artifacts and analyzes the situation concerning 
the applicable heuristics.

> 3. Placing Cards and Defining Bounded Context (30 to 60 minutes)

At least one player now begins to visualize their first bounded context boundary on the 
playground (e.g., by circling) and place the cards that apply as heuristics for the boundary.

This provides scope for discussion, and other players are invited to place their cards and 
explain their perspectives. The moderator is responsible for guiding the group discussion 
so that within 30 to 60 minutes, an initial description for a bounded context is created.

Usually, this discussion already reveals indications for further bounded context candidates, 
and the next step crystallizes through this game dynamic. If not, a player starts again with the visualization of a boundary and placing the cards.

The game ends when all capabilities and functions depicted on the playground are allocated to 
a bounded context and each bounded context is defined.

> Visual Game Progress

@ToDo