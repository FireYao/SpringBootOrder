package com.simpletour.baseQuery;

public enum MatchType {
    equal,        // filed = value
    // the next four types used for subclass of Number
    gt,   // filed > value
    ge,   // field >= value
    lt,              // field < value
    le,      // field <= value

    notEqual,            // field != value
    like,   // field like value
    notLike,    // field not like value
    // the next four types used for subclass of Comparable
    greaterThan,        // field > value
    greaterThanOrEqualTo,   // field >= value
    lessThan,               // field < value
    lessThanOrEqualTo,      // field <= value
    ;
}
