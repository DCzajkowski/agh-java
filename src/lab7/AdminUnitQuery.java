package lab7;

import java.util.Comparator;
import java.util.function.Predicate;

import static app.Helpers.tap;

public class AdminUnitQuery {
    protected AdminUnitList src;
    protected Predicate<AdminUnit> predicate = it -> true;
    protected Comparator<AdminUnit> comparator;
    protected int limit = Integer.MAX_VALUE;
    protected int offset = 0;

    public AdminUnitQuery selectFrom(AdminUnitList src) {
        return tap(this, self -> self.src = src);
    }

    public AdminUnitQuery where(Predicate<AdminUnit> predicate) {
        return tap(this, self -> self.predicate = predicate);
    }

    public AdminUnitQuery and(Predicate<AdminUnit> predicate) {
        return tap(this, self -> self.predicate = self.predicate.and(predicate));
    }

    public AdminUnitQuery or(Predicate<AdminUnit> predicate) {
        return tap(this, self -> self.predicate = self.predicate.or(predicate));
    }

    public AdminUnitQuery sort(Comparator<AdminUnit> comparator) {
        return tap(this, self -> self.comparator = comparator);
    }

    public AdminUnitQuery limit(int limit) {
        return tap(this, self -> self.limit = limit);
    }

    public AdminUnitQuery offset(int offset) {
        return tap(this, self -> self.offset = offset);
    }

    public AdminUnitList execute() {
        return new AdminUnitList(
            (this.comparator == null)
                ? this.src.units.stream().skip(this.offset).filter(this.predicate).limit(this.limit)
                : this.src.units.stream().skip(this.offset).filter(this.predicate).sorted(this.comparator).limit(this.limit)
        );
    }
}
