package at.yedel.antimations.features;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.utils.SwingItemDuck;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.apache.logging.log4j.LogManager;



public class FakeSwingOnDamage {
    private static final FakeSwingOnDamage INSTANCE = new FakeSwingOnDamage();

    public static FakeSwingOnDamage getInstance() {
        return INSTANCE;
    }

    private FakeSwingOnDamage() {}

    public int entityAttacked;
    public long attackTime;

    public void onAttack(Entity entity) {
        entityAttacked = entity.getEntityId();
        attackTime = System.currentTimeMillis();
    }

    public void onHurtAnimation(int entityId) {
        if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().fakeSwingOnDamage) {
            int entityAttacked = FakeSwingOnDamage.getInstance().entityAttacked;
            long attackTime = FakeSwingOnDamage.getInstance().attackTime;
            if (entityId == entityAttacked && (System.currentTimeMillis() - attackTime) < AntimationsConfig.getInstance().fakeSwingPeriod) {
                ((SwingItemDuck) Minecraft.getMinecraft().thePlayer).antimations$swingItemLocally();
            }
        }
    }
}
