# Architecture Enabling through Collaborative Architecture Decisions

## Brief Description of the Tactical Architecture Game

The Tactical Architecture Game is a gamification approach to collaboratively finding the tactical construction plan for software architecture within a team.

The game is based on a set of architecture decisions that collectively represent the construction plan. These decisions provide guiding principles for the tactical architecture that a team should commit to. Due to their significance, these decisions are referred to as Enabler Architecture Decisions.

The Tactical Architecture Game, as described here, is designed for tactical domain-driven design in combination with domain-centric architectural patterns such as Clean, Hexagonal, and Onion Architecture. These fundamental decisions already establish soft standards for the architecture. The Enabler Architecture Decisions describe a concrete solution strategy to be applied to these soft standards.

The Enabler Architecture Decisions span four decision categories:

:nazar_amulet: **Modularization**

:nazar_amulet: **Domain Core**

:nazar_amulet: **Use Cases**

:nazar_amulet: **Mappings**

Each category typically contains multiple decisions.

## Enabler Architecture Decisions

### Decision Category Domain Core

@ToDo

###### Integration into Domain-Centric Architectural Patterns

| Architectural Pattern    | Affected Responsibility Area Two-Way Mapping |
| ------------------------ |:---------------------------------------------|
| Clean Architecture       | `Entities Ring`                              |
| Hexagonal Architecture   | `Domain Hexagon`                             |
| Onion Architecture       | `Domain Service Onion` </br> `Domain Onion`  |

#### :triangular_flag_on_post: Architecture Decision #1: By what principle should the domain model be implemented?

###### Precondition

None

###### Soft Standard

With the decision for domain-driven design, the goal is to develop a domain model and implement it using the patterns of tactical design.

For implementing a domain model, the fundamental decision between Rich Domain Model and Anemic Domain Model must be made. This decision determines how the domain model will be implemented and where business logic and rules will be located.

The Rich Domain Model dictates that business logic is implemented within the domain object (Aggregate, Entity, Value Object). This allows for expressive naming of methods based on business functionality, as opposed to simple set and get methods. It also permits the business validation to ensure the consistency of a (Root) Entity to be implemented directly on the object. The same applies to Value Objects that implement validation of the business value.

An Anemic Domain Model contains only the properties along with the associated getters and setters. Business logic and validation are exclusively implemented in Domain Services. The Anemic Domain Model is considered an anti-pattern, as it often leads to loss of business language and consistency violations. Nevertheless, a team must consciously make this decision.

###### Solution Strategies

:bangbang: Rich Domain Model

:bangbang: Moderately Rich Domain Model

:bangbang: Anemic Domain Model

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #2: How should the Value Object pattern be applied in implementation?

###### Precondition

| Enabling Architecture Decision | Decision                                            |
|--------------------------------|:----------------------------------------------------|
| 5                              | `Rich Domain Model` <br/> `Moderately Rich Domain Model` |

###### Soft Standard

Following the Rich Domain Model often raises the question within teams of whether every business value should be implemented as a Value Object. Complex aggregates can potentially lead to numerous Value Objects.

The recommendation is to always implement business values as Value Objects to enhance business functionality and robustness.

Exceptions can be made if business values do not need to be validated or if business values are elements of a nested Value Object.

Avoiding Value Objects and using only primitive data types for business values is also an option but is considered an anti-pattern. Especially in complex aggregates with a lot of validation logic, this can lead to hard-to-read code over time.

###### Solution Strategies

:bangbang: Every business value is implemented as a Value Object

:bangbang: Only business values that require behavior and/or validation are implemented as Value Objects

:bangbang: Every business value is implemented using primitive data types

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #3: How should the Value Object pattern be implemented?

###### Precondition

| Enabling Architecture Decision | Decision                                                      |
|--------------------------------|:--------------------------------------------------------------|
| 5                              | `Rich Domain Model` <br/> `Moderately Rich Domain Model`      |
| 6                              | `Implementation of Business Values as a separate Object (Value Object pattern)` |

###### Soft Standard

@ToDo

###### Solution Strategies

:bangbang: Every business value is implemented as a Value Object

:bangbang: Only business values that require behavior and/or validation are implemented as Value Objects

:bangbang: Every business value is implemented using primitive data types

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #4: How is business consistency ensured when using the Anemic Domain Model?

###### Precondition

| Enabling Architecture Decision | Decision              |
|--------------------------------|:----------------------|
| 5                              | `Anemic Domain Model` |

###### Soft Standard

When using the Anemic Domain Model, another element must be found for validation logic instead of the business objects themselves. One option is the Domain Service; however, this would place the responsibility for business logic and validation in one pattern. For increased business complexity, it is advisable to introduce a new element in the pattern language, such as a Validator.

###### Solution Strategies

:bangbang: Validation logic in Domain Services

:bangbang: Separate element in the pattern language, associated with the domain core

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #5: How is validation implemented?

###### Precondition

None

###### Soft Standard

Regardless of where validation is located in the pattern language, the development team needs a shared understanding of how validation should be implemented. Options range from native implementation in the used programming language to self-created abstractions to using third-party libraries.

###### Solution Strategies

:bangbang: Native implementation without abstraction

:bangbang: Native implementation with abstraction

:bangbang: Third-party libraries

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #6: What object creation strategy should be used for complex business objects?

###### Precondition

None

###### Soft Standard

A Root Entity, Entity, or Value Object must always ensure a valid business state. The creation of business objects plays a crucial role in this. The tactical design pattern language describes the Factory, which takes responsibility for object creation when it is no longer possible through a simple constructor on the object itself.

As an alternative to the Factory, the Builder pattern can be used, and Static Factory Methods are a good alternative or complement to constructors.

Typically, there is no single creation process for a (Root) Entity. Therefore, a combination of solution strategies for each suitable scenario is a promising approach. Arbitrary and, in the worst case, contradictory use of solution strategies increases the structural complexity of the architecture. Thus, an active approach to this question is recommended.

###### Common scenarios of object creation in domain-centric architectural patterns

1. A user executes use cases where the domain object needs to be created
2. The state of a domain object needs to be read from storage
3. The state of a domain object results from different sources (e.g., storage and external service)
4. Business logic necessitates the (re)creation of domain objects as part of the domain logic control flow

###### Solution Strategies

:bangbang: Factory

:bangbang: Static Method Factory

:bangbang: Constructor

:bangbang: Builder

:bangbang: Your Strategy (Wildcard)

###### Risk if the decision is not consciously made

@ToDo

### Decision Category Use Cases

@ToDo

###### Integration into Domain-Centric Architectural Patterns

| Architectural Pattern    | Affected Responsibility Area Two-Way Mapping |
| ------------------------ |:---------------------------------------------|
| Clean Architecture       | `Use Case Ring`                              |
| Hexagonal Architecture   | `Application Hexagon`                        |
| Onion Architecture       | `Application Onion`                          |

#### :triangular_flag_on_post: Architecture Decision #7: How are use cases segmented?

###### Precondition

None

###### Soft Standard

Use cases are the interfaces of the business application and are defined from the domain's perspective. Various heuristics can be used, and no heuristic is inherently the best. What is crucial is a shared understanding and consistent application, which depends on specific scenarios.

###### Common scenarios and suitable solution strategies

@ToDo

###### Solution Strategies

:bangbang: Use case per function

:bangbang: Use case for all command operations

:bangbang: Use case for all query operations

:bangbang: Use case for all interfaces related to a (Root) Entity

:bangbang: Use case for all interfaces to a technical component (e.g., database) within a business module boundary

:bangbang: Your Strategy (Wildcard)

###### Risk if the decision is not consciously made

@ToDo

### Decision Category Mappings

Decisions about mapping strategies significantly influence the long-term strength of the coupling of architectural components. However, mappings can also be overhead in the short term, so a good balance suitable for different scenarios must be found within the team.

###### Integration into Domain-Centric Architectural Patterns

| Architectural Pattern    | Affected Responsibility Area Two-Way Mapping | Affected Responsibility Area Full Mapping |
| ------------------------ |:---------------------------------------------|:----------------------------------------|
| Clean Architecture       | `Interface Adapters Ring`                    | `Interface Adapters Ring`<br/>`Use Case Ring` |
| Hexagonal Architecture   | `Adapters Hexagon`                           | `Adapters Hexagon`<br/>`Application Hexagon`  |
| Onion Architecture       |

 `Adapters Onion`                             | `Adapters Onion`<br/>`Application Onion`      |

#### :triangular_flag_on_post: Architecture Decision #8: What mapping strategy is used as the default strategy, and which scenarios suit alternative strategies?

###### Precondition

None

###### Soft Standard

This decision has already been explained in the description of Clean Architecture.

###### Common scenarios and suitable solution strategies

@ToDo

###### Solution Strategies

:bangbang: One-Mapping

:bangbang: Two-Way Mapping

:bangbang: Full Mapping

:bangbang: No Mapping

:bangbang: Your Strategy (Wildcard)

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #9: How are mappings implemented?

| Enabling Architecture Decision | Decision                                            |
|--------------------------------|:----------------------------------------------------|
| 12                             | `Two-Way Mapping Strategy` <br/> `Full Mapping Strategy` |

###### Soft Standard

For the Two-Way and Full Mapping strategies, the question of how mappings should be implemented arises. A unified solution strategy is recommended to avoid unnecessary complexity in implementation. Each strategy has its pros and cons, and ultimately it must also suit the team's preferences. Therefore, this decision should be consciously made within the team.

###### Solution Strategies

:bangbang: Native implementation through a separate element in the pattern language (e.g., Mapper)

:bangbang: Native implementation as logic of the Interface Adapter (e.g., private method)

:bangbang: Implementation through a separate element in the pattern language as well as using third-party libraries

:bangbang: Your Strategy (Wildcard)

###### Risk if the decision is not consciously made

@ToDo

### Decision Category Modularization

Enabler Architecture Decisions in the category of modularization have a cross-cutting character and apply uniformly across all domain-centric architectural patterns.

#### :triangular_flag_on_post: Architecture Decision #10: Vertical vs. Horizontal Layering

###### Precondition

None

###### Soft Standard

@ToDo

###### Solution Strategies

:bangbang: Vertical and business-motivated layering at architecture level 1

:bangbang: Technical layering at architecture level 1 with business layering at architecture level 2 and split packages

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #11: How is modularization implemented?

###### Precondition

#### :triangular_flag_on_post: Architecture Decision #12: How are business modules structured?

###### Precondition

| Enabling Architecture Decision | Decision                  |
| ------------------------------ |:--------------------------|
| 1                              | `Vertical Layering`       |

###### Soft Standard

The business module forms around an aggregate.
Within this module, the responsibility areas (Ring, Hexagon, or Onion) of the domain-centric architectural pattern are represented.
Various solution strategies are available for this.

###### Solution Strategies

:bangbang: Architecturally expressive

:bangbang: Architecturally expressive in the business application

:bangbang: Responsibility areas (Ring, Hexagon, or Onion) as layers

###### Risk if the decision is not consciously made

@ToDo

#### :triangular_flag_on_post: Architecture Decision #13: How is the dependency between two aggregates handled?

###### Precondition

None

###### Soft Standard

In tactical domain-driven design, decoupling based on aggregate boundaries is pursued. Aggregates have an independent lifecycle.

If a business connection needs to be established, one aggregate may reference another aggregate only through its identity. The reference must be resolved at runtime.

Such dependencies should be treated uniformly and in a pattern-like manner to keep complexity low and the architecture easily understandable.

Which pattern serves as the standard and in which scenarios alternative solution strategies are used must be collaboratively decided by the team.

###### Solution Strategies

:bangbang: Application Service Pattern

:bangbang: Adapter Out Use Case In Pattern

:bangbang: Event-driven / Publish-Subscribe Pattern

###### Risk if the decision is not consciously made

@ToDo