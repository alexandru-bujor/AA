# Iterated Prisoner's Dilemma Strategy: Advanced Adaptive Algorithm

## Overview
This algorithm is designed for the Iterated Prisoner's Dilemma tournament. It employs a blend of randomized openings, behavioral analysis, and adaptive decision-making to maximize points while maintaining flexibility. The strategy focuses on identifying opponent patterns and responding optimally to ensure a competitive edge.

## Algorithm Logic
1. **Randomized Opening Moves**:
    - The first five moves are randomized (a mix of cooperation and defection) to test the opponent's reactions and collect initial data.

2. **Behavioral Analysis**:
    - The algorithm detects patterns in the opponent's moves:
        - Consistent Cooperation
        - Consistent Defection
        - Alternating Patterns
        - Mixed/Unpredictable Behavior
    - It uses this information to adjust its strategy dynamically.

3. **Decision-Making**:
    - Cooperates more with friendly opponents.
    - Defects against uncooperative or alternating opponents to disrupt their strategy.
    - Uses probabilistic decisions for mixed behaviors to stay unpredictable.

4. **Fallback Mechanism**:
    - If no clear pattern is detected, the algorithm defaults to a "Tit-for-Tat" approach, mimicking the opponent's last move.

## Requirements
- Written in Python and adheres to tournament constraints:
    - Function signature: `def strategy(my_history: list[int], opponent_history: list[int], rounds: int | None) -> int`
    - Compatible with inputs defined by the tournament rules.
    - No external dependencies or libraries used.

## Features
- Highly adaptive and deceptive strategy.
- Lightweight and efficient, meeting the 200ms execution time and 50MB memory constraints.
- Focused on maximizing points while analyzing and countering opponent strategies effectively.

Feel free to enhance and test this algorithm further. Best of luck in the tournament!

Elaborated by FAF-231 Student:

Bujor Alexandru