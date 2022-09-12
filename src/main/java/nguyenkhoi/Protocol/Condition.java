package nguyenkhoi.Protocol;

import org.bukkit.entity.Player;

public abstract class Condition implements ICondition {
    /**
     * Construct Condition and declare the condition() method
     */
    public Condition() {}
}
interface ICondition {
    /**
     * Interface of Condition
     * @param player - the player to check condition
     * @return the condition is met or not
     */
    boolean condition(Player player);
}

