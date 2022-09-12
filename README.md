# ProtocolCore

# Maven
```
<repository>
  <id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```
```
<dependency>
  <groupId>com.github.NguyenPhucAnhKhoi</groupId>
	<artifactId>ProtocolCore</artifactId>
	<version>Version</version>
</dependency>
```

# Example
> Send a normal packet to player
```
//Create BlockPosition instance first
BlockPosition blockPosition = new BlockPosition(x, y, z);
//Then create packet instace
PacketBlockBreakAnimation packet = new PacketBlockBreakAnimation(entityId(), blockPosition, destroyStage);
//Send packet to player
sendPacket(p, packet);
//Or send packet if player is met the condition
sendPacketIf(p, packet, new Condition() {
  @Override
  public boolean condition(Player player) {
    //Send packet if player is Server Op
    return player.isOp();
  }
});
```
