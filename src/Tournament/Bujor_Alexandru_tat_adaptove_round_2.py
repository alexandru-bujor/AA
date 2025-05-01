def strategy_round_2(opponent_id: int, my_history: dict[int, list[int]],
                     opponents_history: dict[int, list[int]]) -> tuple[int, int]:
    MAX_ROUNDS = 200

    # --- Select move against current opponent ---
    opponent_moves = opponents_history.get(opponent_id, [])
    if not opponent_moves:
        move = 1  # cooperate first
    else:
        move = opponent_moves[-1]  # tit-for-tat: repeat last opponent move

    # --- Select next opponent ---
    # Avoid over-played opponents
    candidates = [pid for pid in opponents_history if len(opponents_history[pid]) < MAX_ROUNDS]

    if not candidates:
        next_opponent = opponent_id  # fallback
    else:
        # Prefer the one with highest cooperation rate
        def coop_score(pid):
            history = opponents_history[pid]
            return sum(history) / len(history) if history else 1.0

        next_opponent = max(candidates, key=coop_score)

    return (move, next_opponent)
