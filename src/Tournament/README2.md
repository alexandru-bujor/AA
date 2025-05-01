Iterated Prisoner’s Dilemma – Round 2 Strategy

Strategy Name: Tit for Tat Adaptive

Author: Bujor Alexandru FAF-231

Description

This strategy is based on the Tit for Tat principle with an adaptive opponent selection mechanism:
	•	Initial Move: Cooperates on the first round with each new opponent.
	•	Subsequent Moves: Repeats the last move the opponent made (i.e., Tit for Tat).
	•	Opponent Selection: Prioritizes opponents who:
	•	Have not yet reached the maximum of 200 rounds.
	•	Have higher cooperation rates, calculated as the percentage of times they cooperated in past rounds.

Design Decisions
	•	The strategy favors cooperation and punishes defection only once.
	•	It avoids wasting rounds on uncooperative opponents.
	•	Opponent selection is dynamic, adapting to each opponent’s behavior.

Function Signature
def strategy_round_2(opponent_id: int, my_history: dict[int, list[int]],
                     opponents_history: dict[int, list[int]]) -> tuple[int, int]:

	•	opponent_id: the ID of the current opponent.
	•	my_history: your past moves with each opponent.
	•	opponents_history: how each opponent has played against you.
	•	Returns a tuple:
	•	First element: your next move (0 = defect, 1 = cooperate).
	•	Second element: the ID of the next opponent you wish to face.

Constraints Handled
	•	Never exceeds 200 rounds per opponent.
	•	Execution is deterministic and within time/memory constraints.
	•	No random module, external libraries, or extra output.

Notes
	•	This file contains only the required strategy function.
	•	The strategy is tested to ensure it doesn’t crash under any valid input.
