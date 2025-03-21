# The Context Mapping Game

## Objective of the Game

The objective of the Context Mapping Game is to design dependencies between Bounded Contexts considering 
the following dimensions:

* Call Flow
* Model Flow
* Influence Flow

### The Mission

Defining dependencies between two Bounded Contexts based on the Context Mapping Patterns 
of Domain-Driven Design constitutes a mission for the team. The game ends when all missions 
are completed. This means that all dependencies between Bounded Contexts have been defined.

## Game Preparation

The prerequisite for playing the Context Mapping Game is the familiarity with the 
Bounded Contexts. Each Bounded Context is described using the Bounded Context Canvas. 
The filled-in Bounded Context Canvases are used as the playground, either physically or digitally. 
If subdomains are known, it is recommended to group Bounded Contexts of the same subdomain 
on the playground.

## Game Progress

> 1. Dependency Storming (10 to 20 minutes)

In the first step of the Context Mapping Game, the team chooses the first mission, i.e., 
a Bounded Context to be considered. The mission begins with 10 minutes of brainstorming 
dependencies for the Bounded Context (Dependency Storming). A dependency is expressed as a
* Command,
* Query,
* Event,
* (Sub-)System, or
* Actor.

The dependencies are divided into incoming and outgoing dependencies and placed as sticky notes 
on the playground.

> 2. Grouping Dependencies (10 minutes)

The group now engages in an initial discussion about the identified dependencies and groups 
similar mentions as well as related elements (e.g., matching command or query to a system or 
an actor).

Identified dependencies between Bounded Contexts can optionally be visualized with a 
connecting line on the playground.

> 3. Discussion and Placing Cards (15 minutes)

In the next step, each dependency is discussed within the team with a timebox of 15 minutes. 
Each player has the opportunity to share their view, consider other views, and 
to participate in discussions.

Then, each player places their cards, thus sharing their view on the relationship 
definition between two Bounded Contexts. A dependency is defined by placing 
the **role card** (upstream and downstream) and **pattern card** 
(Context Mapping Pattern for each role).

> 4. Decision Making (15 minutes)

If a common basis with outliers is revealed, the decision-making process can start by explaining
the outliers. If the result is very different or uniform, a voluntary player begins by describing 
his perspective.

Through the exchange within the team, non-fitting patterns and roles are eliminated, 
and cards will be removed from the field. The dependency is considered defined when the 
placed cards provide a valid and consistent depiction of
* the Context Mapping Patterns using pattern cards _and_
* role definition using role Cards _and_
* all players agree on this definition of the relationship.

> Visual Game Progress

@ToDo