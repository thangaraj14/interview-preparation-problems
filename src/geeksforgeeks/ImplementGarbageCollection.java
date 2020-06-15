package geeksforgeeks;

/**
 * https://www.educative.io/courses/a-quick-primer-on-garbage-collection-algorithms/jR2PP
 */
public class ImplementGarbageCollection {

   /* def gc():
    stop_all_mutators()
    mark_roots()
    sweep()
    resume_all_mutators()

    def mark_roots():
    candidates = Stack()
    for field in Roots:
            if field != nil && not is_marked(field):
    set_marked(field)
            candidates.push(field)
    mark(candidates)

    def mark(candidates):
            while not candidates.empty():
    ref = candidates.pop()
            for field in pointers(ref):
            if field != nil && not is_marked(field):
    set_marked(field)
                candidates.push(field)

    def sweep():
    scan = start_of_heap()
    end = end_of_heap()
    while scan < end:
            if is_marked(scan):
    unset_marked(scan)
        else:
    free(scan)
    scan = next_object(scan)

    def next_object(address):
            # Parse the heap and return the next object.
    ...*/
}
