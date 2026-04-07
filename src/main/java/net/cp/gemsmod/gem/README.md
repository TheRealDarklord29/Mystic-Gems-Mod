# `gem` package guide

Core gameplay logic for the gem system lives here.

## Files
- `GemType`: canonical definitions for each gem and its effect metadata.
- `GemAbilityHandler`: active/passive effect application plus particles/sounds.
- `GemTickHandler`: server tick hook for passive updates.
- `GemDropHandler`: mining and mob drop hooks.
- `GemDataManager`: attunement state management and inventory checks.
- `GemDataHolder`: interface for player-attached gem data storage.

## Change checklist
When adding a new gem type:
1. Add it to `GemType`.
2. Wire item registration in `ItemRegistry`.
3. Add translations/models/recipes under `resources`.
4. Optionally add drop and config tuning entries.
