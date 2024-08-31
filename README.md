# How to Use EffectLib

Follow the steps below to effectively use the `MobEffectsUtil` library for creating custom mob effects.

---

## Step 1: Initialize

To begin, initialize the `MobEffectsUtil` with your mod's ID.

```java
MobEffectsUtil.initialize("mod_id_here");
```

---

## Step 2: Create Effect Data

Next, create a new `ModMobEffectData` instance. This data represents the custom effect you want to create. You need to provide the effect's name, category, and color.

**Example:**

```java
ModMobEffectData data = new ModMobEffectData("my_effect", MobEffectCategory.NEUTRAL, 0);
```

*Parameters:*
- **effect_name**: A string representing the name of the effect.
- **effect_category**: The category of the effect, such as `MobEffectCategory.NEUTRAL`.
- **decimal_color**: An integer representing the color of the effect in decimal format.

---

## Step 3: Add Optional Effect Behaviors

You can add custom behaviors to your effect using various methods:

### On Start Behavior

Add a function that will be called when the effect starts.

```java
data.addOnStart(func); 
// or
data.addOnStart((entity, amplifier) -> {
    // Your code here
});
```

### On Tick Behavior

Add a function that will be called every tick while the effect is active.

```java
data.addOnTick(func);
// or
data.addOnTick((entity, amplifier) -> {
    // Your code here
});
```

### On End Behavior

Add a function that will be called when the effect expires.

```java
data.addOnEnd(func); 
// or
data.addOnEnd((entity, amplifier) -> {
    // Your code here
});
```

### Set Tick Delay

Set the delay between each `onTick` function call. This value is specified in ticks.

```java
data.setTickDelay(int delay);
```

---

## Step 4: Create the Effect

Once you have configured your effect data, create the effect using the following method:

```java
MobEffectsUtil.createEffect(data);
```

## Step 5: Register the Effect

Finally, register the effect with the event bus to make it available in your mod.

```java
MobEffectsUtil.register(IEventBus bus);
```

---

That's it! You have successfully created and registered a custom mob effect using `MobEffectsUtil`. Customize the behavior of your effects further by modifying the functions and parameters as needed. Enjoy creating unique experiences in your mod!
